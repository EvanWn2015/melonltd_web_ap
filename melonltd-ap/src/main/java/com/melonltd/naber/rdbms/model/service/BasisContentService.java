package com.melonltd.naber.rdbms.model.service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.bean.BasisContent;
import com.melonltd.naber.rdbms.model.dao.BasisContentDao;
import com.melonltd.naber.rdbms.model.vo.AdministrativeRegionsVo;
import com.melonltd.naber.rdbms.model.vo.SchoolDividedVo;

@Service("basisContentService")
public class BasisContentService {
//	private static final Logger LOGGER = LoggerFactory.getLogger(BasisContentService.class);

	@Autowired
	private BasisContentDao basisContentDao;

	static class BasisContentId implements Serializable{
		private static final long serialVersionUID = -5377143031576443368L;
		private String type;
		private String function;

		BasisContentId(String type, String function) {
			this.type = type;
			this.function = function;
		}

		public String getType() {
			return type;
		}

		public String getFunction() {
			return function;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			result = prime * result + ((function == null) ? 0 : function.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BasisContentId other = (BasisContentId) obj;
			if (function == null) {
				if (other.function != null)
					return false;
			} else if (!function.equals(other.function))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			return true;
		}
		
		public static BasisContentId of(String type, String function) {
			return new BasisContentId(type, function);
		}
	}

	LoadingCache<BasisContentId, BasisContent> cacheBuilder = CacheBuilder.newBuilder()
			.build(new CacheLoader<BasisContentId, BasisContent>() {
				@Override
				public BasisContent load(BasisContentId cacheId) throws Exception {
					BasisContent info = basisContentDao.findByTypeAndFunction(cacheId.getType(), cacheId.getFunction());
					return info;
				}
			});
	
	public BasisContent getAppIntro() {
		BasisContentId id = BasisContentId.of("APP_INTRO", "BULLETIN");
		return findByCacheId(id);
	}

	public BasisContent getStoreAreas() {
		BasisContentId id = BasisContentId.of("AREA", "LIST");
		return findByCacheId(id);
	}

	public BasisContent getStoreCategorys() {
		BasisContentId id = BasisContentId.of("CATEGORY", "LIST");
		return findByCacheId(id);
	}
	
	public List<AdministrativeRegionsVo> findSubjectionRegions() {
		BasisContentId id = BasisContentId.of("SUBJECTION", "REGIONS");
		BasisContent content = findByCacheId(id);
		if (ObjectUtils.anyNotNull(content)) {
			return JsonHelper.jsonArray(content.getContent(), AdministrativeRegionsVo[].class);
		}
		return Lists.<AdministrativeRegionsVo>newArrayList();
	}
	
	public List<SchoolDividedVo> findSchoolDivided() {
		BasisContentId id = BasisContentId.of("SCHOOL", "DIVIDED");
		BasisContent content = findByCacheId(id);
		if (ObjectUtils.anyNotNull(content)) {
			return JsonHelper.jsonArray(content.getContent(), SchoolDividedVo[].class);
		}
		return Lists.<SchoolDividedVo>newArrayList();
	}
	
	public BasisContent updateStoreAreas(String content) {
		BasisContentId id = BasisContentId.of("AREA", "LIST");
		cacheBuilder.invalidate(id);
		BasisContent info = basisContentDao.findByTypeAndFunction(id.getType(), id.getFunction());
		info.setContent(content);
		cacheBuilder.put(id, basisContentDao.save(info));
		return findByCacheId(id);
	}

	public BasisContent updateStoreCategorys(String content) {
		BasisContentId id = BasisContentId.of("CATEGORY", "LIST");
		cacheBuilder.invalidate(id);
		BasisContent info = basisContentDao.findByTypeAndFunction(id.getType(), id.getFunction());
		info.setContent(content);
		cacheBuilder.put(id, basisContentDao.save(info));
		return findByCacheId(id);
	}
	
	public BasisContent findByCacheId(BasisContentId id) {
		try {
			return cacheBuilder.get(id);
		} catch (ExecutionException e) {
			e.printStackTrace();
			BasisContent info = basisContentDao.findByTypeAndFunction(id.getType(), id.getFunction());
			if (ObjectUtils.allNotNull(info)) {
				cacheBuilder.put(id, info);	
			}
			return info;
		}
	}
}
