package no.difi.sdp.client2.domain.fysisk_post;

import org.junit.Test;

import static no.difi.sdp.client2.domain.fysisk_post.KonvoluttAdresse.Type.NORSK;
import static no.difi.sdp.client2.domain.fysisk_post.KonvoluttAdresse.Type.UTENLANDSK;
import static no.difi.sdp.client2.domain.fysisk_post.Landkoder.Predefinert.USA;
import static org.fest.assertions.api.Assertions.assertThat;

public class PostadresseBuilderTest {

	@Test
	public void inkluderer_kun_ikke_null_adresselinjer() {
		KonvoluttAdresse adresse = KonvoluttAdresse.build("Ola Hansen").iNorge("Osloveien 5", null, null, "0560", "Oslo").build();
		assertThat(adresse.getAdresselinjer()).containsExactly("Osloveien 5");

		adresse = KonvoluttAdresse.build("Ola Hansen").iUtlandet("Somewhere St. 5", null, "70482 City", null, USA).build();
		assertThat(adresse.getAdresselinjer()).containsExactly("Somewhere St. 5", "70482 City");
	}

	@Test
	public void norsk_adresse_er_norsk() {
		KonvoluttAdresse adresse = KonvoluttAdresse.build("Ola Hansen").iNorge("Osloveien 5", null, null, "0560", "Oslo").build();
		assertThat(adresse.getType()).isEqualTo(NORSK);
	}

	@Test
	public void utenlandsk_adresse_er_utenlandsk() {
		KonvoluttAdresse adresse = KonvoluttAdresse.build("Ola Hansen").iUtlandet("Somewhere St. 5", "10592 New York", null, null, USA).build();
		assertThat(adresse.getType()).isEqualTo(UTENLANDSK);
		assertThat(adresse.getLand()).isNull();
		assertThat(adresse.getLandkode()).isEqualTo(USA.getKode());
	}

	@Test
	public void utenlandsk_adresse_med_landnavn_kan_hente_navn() {
		KonvoluttAdresse adresse = KonvoluttAdresse.build("Ola Hansen").iUtlandet("Somewhere St. 5", "10592 New York", null, null, "Sverige").build();
		assertThat(adresse.getType()).isEqualTo(UTENLANDSK);
		assertThat(adresse.getLand()).isEqualTo("Sverige");
		assertThat(adresse.getLandkode()).isNull();
	}

}
