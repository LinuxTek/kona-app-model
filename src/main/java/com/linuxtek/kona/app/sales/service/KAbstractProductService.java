package com.linuxtek.kona.app.sales.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linuxtek.kona.app.core.service.KAbstractService;
import com.linuxtek.kona.app.sales.entity.KProduct;
import com.linuxtek.kona.data.mybatis.KMyBatisUtil;

public abstract class KAbstractProductService<PRODUCT extends KProduct, 
										      PRODUCT_EXAMPLE>
		extends KAbstractService<PRODUCT,PRODUCT_EXAMPLE>
		implements KProductService<PRODUCT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractProductService.class);

	// ----------------------------------------------------------------------------
    
    @Override
    public void validate(PRODUCT product) {
        if (product.getCreatedDate() == null) {
        	product.setCreatedDate(new Date());
        }
        
        product.setUpdatedDate(new Date());
    }
    
	// ----------------------------------------------------------------------------

    /*
    @Override
    // FIXME: make sure that when adding/remove/updating products, the default
    // product is handled properly.
    public Product fetchDefault(Long appId) {
        Product defaultProduct = null;
        if (defaultProduct == null) {
            ProductExample example = new ProductExample();
            example.or()
            	.andAppIdEqualTo(appId)
            	.andDefaultPlanEqualTo(true);

                List<Product> result = 
                        productDao.selectByExample(example);
                if (result != null && result.size() >0) {
                    defaultProduct = result.get(0);
                }
        }
        return defaultProduct;
    }
    */
	// ----------------------------------------------------------------------------
    
    @Override
    public List<PRODUCT> fetchAll(Long appId, Boolean active) {
        Map<String,Object> filter = null;
        if (appId != null || active != null) {
        	if (active != null) {
        		filter = KMyBatisUtil.createFilter("active", active);
        	}
            
        	if (appId != null) {
                if (filter != null) {
                	filter.put("appId", appId);
                } else {
                	filter = KMyBatisUtil.createFilter("appId", appId);
                }
        	}
        }
		return fetchByCriteria(0, 99999, null, filter, false);
    }
    
	// ----------------------------------------------------------------------------
    
    @Override
    public PRODUCT fetchByName(Long appId, String name) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("name", name);
        filter.put("appId", appId);
		return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
}
