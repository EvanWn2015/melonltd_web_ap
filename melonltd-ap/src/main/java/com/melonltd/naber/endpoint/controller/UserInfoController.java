package com.melonltd.naber.endpoint.controller;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.TestTable;
import com.melonltd.naber.rdbms.model.service.TestTableService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({""})
public class UserInfoController
{
  private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
  private static final int PAGE_SIZE = 10;
  @Autowired
  private TestTableService service;
  
  @RequestMapping(value={"user"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public ResponseEntity<String> getAllUserByPage(@RequestParam(value="page", required=false, defaultValue="0") int page)
  {
    page = page > 0 ? page - 1 : page;
    Sort sort = new Sort(Sort.Direction.ASC, new String[] { "id" });
    Pageable pageable = new PageRequest(page, 10, sort);
    Page<TestTable> tests = this.service.findAllByPage(pageable);
    String result = Base64Service.encryptBASE64(JsonHelper.toJson(tests.getContent()));
    return new ResponseEntity(result, HttpStatus.OK);
  }
  
  @RequestMapping(value={"users"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public ResponseEntity<String> getAllUsers()
  {
    List<TestTable> tests = this.service.findAll();
    String result = Base64Service.encryptBASE64(JsonHelper.toJson(tests));
    return new ResponseEntity(result, HttpStatus.OK);
  }
}
