/*
 * Copyright (c) 2017 Ping Identity
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pingidentity.labs.dtva.application.impl.views;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.pingidentity.labs.dtva.application.Issuer;
import com.pingidentity.labs.dtva.application.ValidityKey;

public class InvalidatedView extends BaseView {

	private final @NotNull Instant invalidatedAt;

	public InvalidatedView(@NotNull ValidityKey validityKey, @NotNull Issuer issuer, @NotNull Instant evaluatedAt,  @NotNull Instant invalidatedAt) {
		super(validityKey, issuer, evaluatedAt);
		this.invalidatedAt = invalidatedAt;
	}
	@Override
	public String getStateName() {
		return "invalidated";
	}


	@Override
	public Instant getScheduledTransitionAt() {
		return getHardExpiryAt();
	}

	@Override
	public Duration untilNextTransition() {
		return untilHardExpiry();
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public boolean isExpired() {
		return true;
	}

	@Override
	public boolean isInvalidated() {
		return true;
	}

	@Override
	public Instant getLastModifiedAt() {
		return invalidatedAt;
	}

	@Override
	public Optional<Instant> getInvalidatedAt() {
		return Optional.of(invalidatedAt);
	}

}
