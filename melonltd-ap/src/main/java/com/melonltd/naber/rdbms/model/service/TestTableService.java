package com.melonltd.naber.rdbms.model.service;

import com.melonltd.naber.rdbms.model.bean.TestTable;
import com.melonltd.naber.rdbms.model.dao.TestTableDao;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TestTableService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestTableService.class);
	@Autowired
	private TestTableDao dao;

	public List<TestTable> findAll() {
		return this.dao.findAll();
	}

	public Page<TestTable> findAllByPage(Pageable pageable) {
		return this.dao.findAll(pageable);
	}
}
