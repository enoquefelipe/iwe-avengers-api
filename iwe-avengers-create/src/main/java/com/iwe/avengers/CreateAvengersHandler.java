package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.dao.AvengerDao;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		final Avenger avengerRetrieved = dao.create(avenger);

		context.getLogger().log("[#] - Avenger found " + avengerRetrieved.getName());

		final HandlerResponse response = HandlerResponse.builder().setStatusCode(201).setObjectBody(avengerRetrieved).build();

		return response;
	}
	
}
