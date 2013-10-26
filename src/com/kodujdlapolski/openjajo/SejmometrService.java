package com.kodujdlapolski.openjajo;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.kodujdlapolski.openjajo.sejmometr.GminaSet;

public interface SejmometrService {

	@GET("/requests/localize")
	GminaSet localize(@Query("latitude") String latitude,
			@Query("longitude") String longitude);

	@GET("/bdl_wskazniki/{id}/{level}")
	com.kodujdlapolski.openjajo.sejmometr.voivodeship.DocumentSet voivodship_indicator(
			@Path("id") Integer id, @Path("level") String level,
			@Query("k") Integer k, @Query("w_id") Integer voivodship);

	/**
	 * 
	 * @param gmina_id
	 *            id of the polish gmina
	 * @param id
	 *            podgroup id of indicators
	 * @param k
	 *            id od particular indicator
	 * @return
	 */
	@GET("/gminy/{gmina_id}/bdl_wskazniki/{id}")
	com.kodujdlapolski.openjajo.sejmometr.gmina.DocumentSet gmina_indicator(
			@Path("gmina_id") Integer gmina_id, @Path("id") Integer id,
			@Query("k") Integer k);
}
