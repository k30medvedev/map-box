package com.example.map.box.demo.controller;

import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class ServiceBox {
    public String getCity(String request) {
        return new String("Brest");
    }

    @Value("${mapbox.token}")
    private String token;

    public List<LocationResponse> getLocations(final LocationCriteria criteria) {
        final List<LocationResponse> locations = new ArrayList<>();

        if (StringUtils.isNotBlank(criteria.getSearch())) {
            try {
                final MapboxGeocoding geocoding = buildMapboxGeocoding(criteria.getSearch());

                Optional.ofNullable(geocoding.executeCall())
                        .map(Response::body)
                        .map(GeocodingResponse::features)
                        .orElseGet(ArrayList::new)
                        .stream()
                        .map(CarmenFeature::placeName)
                        .map(LocationResponse::new)
                        .forEach(locations::add);

            } catch (final Exception e) {
                log.error("Error occurred: ", e);
            }
            return locations;
        }
        return locations;
    }

    private MapboxGeocoding buildMapboxGeocoding(final String query) {
        return MapboxGeocoding.builder()
                .accessToken(token)
                .query(query)
                .autocomplete(true)
                .limit(10)
                .build();
    }
}
