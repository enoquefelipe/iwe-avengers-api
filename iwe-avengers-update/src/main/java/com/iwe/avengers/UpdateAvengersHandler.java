package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.dao.AvengerDao;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;

public class UpdateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		Avenger updateAvenger = null;
		
		context.getLogger().log("[#] - Searching avenger by id:  " + avenger.getId());
		
		if(dao.search(avenger.getId()) != null) {
			context.getLogger().log("[#] - Avenger found, updating...");
			updateAvenger = dao.saveOrUpdate(avenger);
			context.getLogger().log("[#] - Avenger updating successfully");
		} else {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avenger.getId());
		}
		
		final HandlerResponse response = HandlerResponse.builder().setStatusCode(200).setObjectBody(updateAvenger).build();

		return response;
	}
	
}
