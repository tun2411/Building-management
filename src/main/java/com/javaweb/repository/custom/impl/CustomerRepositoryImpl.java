package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    private String buildJoin(CustomerSearchRequest customerSearchRequest) {
        StringBuilder joinClause = new StringBuilder();
        if (customerSearchRequest.getStaffId() != null) {
            joinClause.append(" join assignmentcustomer c on cus.id = c.customerid ");
        }
        return joinClause.toString();
    }

    private String buildWhere(CustomerSearchRequest customerSearchRequest) {
        StringBuilder whereClause = new StringBuilder(" WHERE 1=1 ");
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String key = item.getName();
                Object value = item.get(customerSearchRequest);

                if (value != null && !key.equals("staffId")) {
                    if (StringUtils.isNumber(value.toString())) {
                        whereClause.append(" AND cus.").append(key).append(" = ").append(value);
                    } else {
                        if(value!=""){
                            whereClause.append(" AND cus.").append(key).append(" LIKE '%").append(value).append("%'");
                        }
                    }
                }
            }
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (customerSearchRequest.getStaffId() != null) {
            whereClause.append(" AND c.staffid = " + customerSearchRequest.getStaffId());
        }
        return whereClause.toString();
    }


    @Override
    public List<CustomerEntity> searchCustomers(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT cus.* FROM customer cus ");
        sql.append(buildJoin(customerSearchRequest));
        sql.append(buildWhere(customerSearchRequest));
        sql.append(" GROUP BY cus.id ");
        sql.append("ORDER BY cus.createddate DESC ");
        sql.append(" LIMIT ").append(pageable.getPageSize());
        sql.append(" OFFSET ").append(pageable.getOffset());
        System.out.print(sql);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        List<CustomerEntity> T = query.getResultList();
        return T;
    }

    @Override
    public int countTotalItem(CustomerSearchRequest request) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT cus.* FROM customer cus ");
        sql.append(buildJoin(request));
        sql.append(buildWhere(request));
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        List<CustomerEntity> T = query.getResultList();
        return T.size();
    }

}
