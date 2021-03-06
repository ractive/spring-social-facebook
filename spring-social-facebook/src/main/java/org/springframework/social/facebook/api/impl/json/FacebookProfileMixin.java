/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api.impl.json;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.social.facebook.api.AgeRange;
import org.springframework.social.facebook.api.EducationEntry;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.WorkEntry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to FacebookProfile. 
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class FacebookProfileMixin extends FacebookObjectMixin {

	@JsonCreator
	FacebookProfileMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("username") String username, 
			@JsonProperty("name") String name, 
			@JsonProperty("first_name") String firstName, 
			@JsonProperty("last_name") String lastName, 
			@JsonProperty("gender") String gender, 
			@JsonProperty("locale") Locale locale) {}
	
	@JsonProperty("middle_name")
	String middleName;

	@JsonProperty("work")
	List<WorkEntry> work;
	
	@JsonProperty("education")
	List<EducationEntry> education;
	
	@JsonProperty("email")
	String email;
	
	@JsonProperty("link")
	String link;
	
	@JsonProperty("third_party_id")
	String thirdPartyId;
	
	@JsonProperty("timezone")
	Float timezone;
	
	@JsonProperty("updated_time")
	Date updatedTime;
	
	@JsonProperty("verified")
	Boolean verified; 
	
	@JsonProperty("about")
	String about;
	
	@JsonProperty("bio")
	String bio;
	
	@JsonProperty("birthday")
	String birthday;
	
	@JsonProperty("location")
	Reference location;
	
	@JsonProperty("hometown")
	Reference hometown;
	
	@JsonProperty("interested_in")
	List<String> interestedIn;
	
	@JsonProperty("inspirational_people")
	List<Reference> inspirationalPeople;

	@JsonProperty("languages")
	List<Reference> languages;
	
	@JsonProperty("sports")
	List<Reference> sports;
	
	@JsonProperty("favorite_teams")
	List<Reference> favoriteTeams;
	
	@JsonProperty("favorite_athletes")
	List<Reference> favoriteAthletes;

	@JsonProperty("religion")
	String religion;

	@JsonProperty("political")
	String political;
	
	@JsonProperty("quotes")
	String quotes;
	
	@JsonProperty("relationship_status")
	String relationshipStatus;
	
	@JsonProperty("significant_other")
	Reference significantOther;
	
	@JsonProperty("website")
	String website;
	
	@JsonProperty("age_range")
	@JsonDeserialize(using=AgeRangeDeserializer.class)
	AgeRange ageRange;

	private static class AgeRangeDeserializer extends JsonDeserializer<AgeRange> {
		@Override
		public AgeRange deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			JsonNode ageRangeNode = jp.readValueAs(JsonNode.class);
			JsonNode minNode = (JsonNode) ageRangeNode.get("min");
			JsonNode maxNode = (JsonNode) ageRangeNode.get("max");
			Integer min = minNode != null ? minNode.asInt() : null;
			Integer max = maxNode != null ? maxNode.asInt() : null;
			return AgeRange.fromMinMax(min, max);
		}
	}
}
