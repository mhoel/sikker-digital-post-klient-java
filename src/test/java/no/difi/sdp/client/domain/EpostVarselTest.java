/**
 * Copyright (C) Posten Norge AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.difi.sdp.client.domain;

import no.difi.sdp.client.domain.digital_post.EpostVarsel;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class EpostVarselTest {

	@Test
	public void standardEpostVarselErEtter0og7dager() {
		EpostVarsel epostvarsel = EpostVarsel.builder("a@b.no", "varslingstekst").build();
		assertThat(epostvarsel.getDagerEtter()).containsExactly(0, 7);
	}

	@Test
	public void kanTilpasseNaarEpostVarselSendes() {
		EpostVarsel epostvarsel = EpostVarsel.builder("a@b.no", "varslingstekst").varselEtterDager(2, 5, 10).build();
		assertThat(epostvarsel.getDagerEtter()).containsExactly(2, 5, 10);
	}
}
