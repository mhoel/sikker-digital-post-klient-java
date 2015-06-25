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
package no.difi.sdp.client.domain.digital_post;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class EpostVarsel extends Varsel {

    private String epostadresse;

    private EpostVarsel(String epostadresse, String varslingsTekst, List<Integer> etterDager) {
        super(varslingsTekst, etterDager);
        this.epostadresse = epostadresse;
    }

    public String getEpostadresse() {
        return epostadresse;
    }

    /**
     * Oppretter epostvarsel til gitt epostadresse og med gitt tekst. Som standard
     * vil varselet sendes samme dato brevet tilgjengeliggjøres i mottakers
     * digitale postkasse, samt et tilleggsvarsel etter 7 dager dersom mottaker ikke har
     * skaffet seg tilgang til brevet.
     * Dersom man ønsker å tilpasse antall og når varsler sendes kan
     * {@link EpostVarsel.Builder#varselEtterDager(List)} brukes.
     * <p>
     * Ref:
     * <a href="http://begrep.difi.no/SikkerDigitalPost/begrep/Varsler">begrep.difi.no/SikkerDigitalPost/begrep/Varsler</a>
     *
     * @param epostadresse Mottakerens epostadresse som det skal sendes varsel til.
     * @param varslingsTekst Avsenderstyrt varslingstekst som skal inngå i varselet.
     */
    public static Builder builder(String epostadresse, String varslingsTekst) {
        return new Builder(epostadresse, varslingsTekst, asList(0, 7));
    }

    public static class Builder {
        private EpostVarsel target;
        private boolean built = false;

        private Builder(String epostadresse, String varslingsTekst, List<Integer> etterDager) {
            target = new EpostVarsel(epostadresse, varslingsTekst, etterDager);
        }

        /**
         * @see #varselEtterDager(List)
         */
        public Builder varselEtterDager(Integer ... varselEtterDager) {
        	return varselEtterDager(asList(varselEtterDager));
        }

        /**
         * Antall dager etter brevet er tilgjengeliggjort for mottaker første, andre osv varsel skal sendes.
         *
         * Eksempel: 0, 2, 5, 10
         * Hvis brevet blir tilgjengeliggjort 1.7.2014 vil det bli sendt varsel:
         * <ul>
         *     <li>1.7.2014</li>
         *     <li>3.7.2014</li>
         *     <li>6.7.2014</li>
         *     <li>11.7.2014</li>
         * </ul>
         *
         * Det vil ikke bli sendt flere varsler etter at mottakeren har åpnet brevet.
         *
         * Standard er ett varsel samtidig som brevet blir tilgjengeliggjort for mottaker.
         */
        public Builder varselEtterDager(List<Integer> varselEtterDager) {
            target.dagerEtter = new ArrayList<Integer>(varselEtterDager);
            return this;
        }

        public EpostVarsel build() {
            if (built) throw new IllegalStateException("Can't build twice");
            built = true;
            return target;
        }
    }
}
