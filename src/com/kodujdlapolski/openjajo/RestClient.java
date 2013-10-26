package com.kodujdlapolski.openjajo;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

import com.kodujdlapolski.openjajo.sejmometr.GminaSet;
import com.kodujdlapolski.openjajo.sejmometr.gmina.DetailedGminaData;
import com.kodujdlapolski.openjajo.sejmometr.voivodeship.DetailedVoiovodeshipData;

public class RestClient implements Client {
	private final String uri;

	public RestClient(String uri) {
		this.uri = uri;
	}

	@Override
	public GminaSet getLocalization(String latitude, String longitude) {
		RestAdapter restAdapter = createRestAdapter();

		SejmometrService service = restAdapter.create(SejmometrService.class);

		GminaSet localize = service.localize(latitude, longitude);
		return localize;
	}

	@Override
	public DetailedVoiovodeshipData[] getVoivodeshipIndicator(Integer subGroup,
			Integer k, Integer voiwodship) {
		RestAdapter restAdapter = createRestAdapter();
		SejmometrService service = restAdapter.create(SejmometrService.class);
		com.kodujdlapolski.openjajo.sejmometr.voivodeship.DocumentSet documentSet = service
				.voivodship_indicator(subGroup, "wojewodztwa", k, voiwodship);
		return documentSet.getDocument().getContent().getLayers()
				.getDetailedData().getData();
	}

	@Override
	public DetailedGminaData[] getGminaIndicator(Integer subGroup, Integer k,
			Integer gmina) {
		RestAdapter restAdapter = createRestAdapter();
		SejmometrService service = restAdapter.create(SejmometrService.class);
		com.kodujdlapolski.openjajo.sejmometr.gmina.DocumentSet documentSet = service
				.gmina_indicator(gmina, subGroup, k);
		return documentSet.getDocument().getContent().getLayers()
				.getDetailedData().getData();
	}

	private RestAdapter createRestAdapter() {
		RequestInterceptor requestInterceptor = new RequestInterceptor() {
			@Override
			public void intercept(RequestFacade request) {
				request.addHeader("Accept", "application/vnd.EPF_API.v1+json");
			}
		};
		return new RestAdapter.Builder()
				.setRequestInterceptor(requestInterceptor).setServer(uri).
				// setLogLevel(RestAdapter.LogLevel.FULL).
				build();
	}
}
