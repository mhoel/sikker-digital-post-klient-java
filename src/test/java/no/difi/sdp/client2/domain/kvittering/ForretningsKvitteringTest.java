package no.difi.sdp.client2.domain.kvittering;

import no.digipost.api.representations.KanBekreftesSomBehandletKvittering;
import no.digipost.api.representations.KvitteringsReferanse;
import org.junit.Test;

import java.time.Instant;

import static org.fest.assertions.api.Assertions.assertThat;

public class ForretningsKvitteringTest {

    @Test
    public void constructor_initializes_properly() {
        KanBekreftesSomBehandletKvittering kanBekreftesSomBehandletKvittering = new KanBekreftesSomBehandletKvittering() {
            @Override
            public String getMeldingsId() {
                return "MeldingsId";
            }

            @Override
            public KvitteringsReferanse getReferanseTilMeldingSomKvitteres() {
                return null;
            }
        };
        KvitteringsInfo kvitteringsInfo = new KvitteringsInfo("konversasjonsId", "referanseTilMeldingId", Instant.now());
        LeveringsKvittering leveringsKvittering = new LeveringsKvittering(kanBekreftesSomBehandletKvittering, kvitteringsInfo);

        assertThat(leveringsKvittering.getReferanseTilMeldingSomKvitteres()).isEqualTo(kanBekreftesSomBehandletKvittering.getReferanseTilMeldingSomKvitteres());
        assertThat(leveringsKvittering.getMeldingsId()).isEqualTo(kanBekreftesSomBehandletKvittering.getMeldingsId());
        assertThat(leveringsKvittering.getKonversasjonsId()).isEqualTo(kvitteringsInfo.getKonversasjonsId());
        assertThat(leveringsKvittering.getReferanseTilMeldingId()).isEqualTo(kvitteringsInfo.getReferanseTilMeldingId());
        assertThat(leveringsKvittering.getTidspunkt()).isEqualTo(kvitteringsInfo.getTidspunkt());
    }
}