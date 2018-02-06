package com.ge.current.div.dataFeed.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ge.current.div.dataFeed.entity.DivSecurityEntity;



public interface IDivSecurityRepository extends PagingAndSortingRepository <DivSecurityEntity,String> {

	String GET_GROUP = "select divs.groupName from DivSecurityEntity divs where divs.sso =:sso";

	@Query(GET_GROUP)
	public String getRole(@Param("sso") String sso);
}
