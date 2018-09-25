package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.dao.AvengerDao;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.exception.AvengerNotFoundException;
import com.iwe.avenger.lambda.response.HandlerResponse;

public class SearchAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDao dao = new AvengerDao();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		final String id = avenger.getId();

		context.getLogger().log("[#] - Initiate search Avenger by id: " + id);

		final Avenger avengerRetrieved = dao.search(id);

		if (avengerRetrieved == null) {
			throw new AvengerNotFoundException("[NotFound] - Avenger id: " + avenger.getId());
		}

		context.getLogger().log("[#] - Avenger found " + avengerRetrieved.getName());

		final HandlerResponse response = HandlerResponse.builder().setStatusCode(200).setObjectBody(avengerRetrieved)
				.build();

		return response;
	}
}