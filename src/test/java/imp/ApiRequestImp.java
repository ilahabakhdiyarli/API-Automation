package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApiRequestImp {

    private final ApiHelper api = new ApiHelper();

    private void saxla(Response r) {
        ScenarioDataStore.put("response", r);
    }

    private Response cavab() {
        return (Response) ScenarioDataStore.get("response");
    }


    @Step("<endpoint> endpointinə GET sorğusu göndərilir")
    public void getGonder(String endpoint) {
        saxla(api.get(endpoint));
    }

    @Step("Cavabın status kodu <code> olur")
    public void statusYoxla(int code) {
        Assert.assertEquals(cavab().statusCode(), code,
                "Status kodu gözləniləndən fərqlidir");
    }


    @Step("Cavab boş deyil")
    public void cavabBosDeyil() {
        String body = cavab().getBody().asString();
        Assert.assertNotNull(body, "Cavab body null-dur");
        Assert.assertFalse(body.trim().isEmpty(), "Cavab body boşdur");
    }


    @Step("<jsonPath> açarı null deyil")
    public void acarNullDeyil(String jsonPath) {
        Object value = cavab().jsonPath().get(jsonPath);
        Assert.assertNotNull(value, "'" + jsonPath + "' açarının dəyəri null-dur");
    }


    @Step("<jsonPath> açarının dəyəri <expected> olur")
    public void acarDeyeriMetn(String jsonPath, String expected) {
        Object actual = cavab().jsonPath().get(jsonPath);
        Assert.assertNotNull(actual, "'" + jsonPath + "' null-dur");
        Assert.assertEquals(String.valueOf(actual), expected,
                "'" + jsonPath + "' dəyəri uyğun gəlmir");
    }


    @Step("<jsonPath> açarının ədədi dəyəri <expected> olur")
    public void acarDeyeriEded(String jsonPath, double expected) {
        Object actual = cavab().jsonPath().get(jsonPath);
        Assert.assertNotNull(actual, "'" + jsonPath + "' null-dur");
        double a = Double.parseDouble(String.valueOf(actual));
        Assert.assertEquals(a, expected, 0.001,
                "'" + jsonPath + "' ədədi dəyəri uyğun gəlmir");
    }


    @Step("<jsonPath> açarının dəyəri <min> -dən böyükdür")
    public void acarBoyukdur(String jsonPath, double min) {
        Object actual = cavab().jsonPath().get(jsonPath);
        Assert.assertNotNull(actual, "'" + jsonPath + "' null-dur");
        double a = Double.parseDouble(String.valueOf(actual));
        Assert.assertTrue(a > min,
                "'" + jsonPath + "' (" + a + ") " + min + "-dən böyük deyil");
    }


    @Step("<listPath> massivi boş deyil")
    public void massivBosDeyil(String listPath) {
        List<?> list = cavab().jsonPath().getList(listPath);
        Assert.assertNotNull(list, "'" + listPath + "' massivi null-dur");
        Assert.assertFalse(list.isEmpty(), "'" + listPath + "' massivi boşdur");
    }


    @Step("<listPath> massivinin hər elementində <keys> açarları null deyil")
    public void herElementdeAcarlar(String listPath, String keys) {
        List<Map<String, Object>> list = cavab().jsonPath().getList(listPath);
        Assert.assertNotNull(list, "'" + listPath + "' massivi null-dur");
        Assert.assertFalse(list.isEmpty(), "'" + listPath + "' massivi boşdur");
        String[] acarlar = keys.split("\\s*,\\s*");
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> element = list.get(i);
            for (String acar : acarlar) {
                Assert.assertTrue(element.containsKey(acar),
                        i + "-ci elementdə '" + acar + "' açarı yoxdur");
                Assert.assertNotNull(element.get(acar),
                        i + "-ci elementdə '" + acar + "' açarının dəyəri null-dur");
            }
        }
    }


    @Step("<valuesPath> dəyərlərinin hamısı <max> -dən böyük deyil")
    public void hamiMaxdanKicik(String valuesPath, double max) {
        List<Object> values = cavab().jsonPath().getList(valuesPath);
        Assert.assertNotNull(values, "'" + valuesPath + "' tapılmadı");
        Assert.assertFalse(values.isEmpty(), "'" + valuesPath + "' boşdur");
        for (Object v : values) {
            double d = Double.parseDouble(String.valueOf(v));
            Assert.assertTrue(d <= max,
                    valuesPath + " = " + d + " → maksimum " + max + "-i keçir");
        }
    }

    @Step("<countPath> açarı <listPath> massivinin ölçüsünə bərabərdir")
    public void sayMassiveBeraber(String countPath, String listPath) {
        int count = cavab().jsonPath().getInt(countPath);
        List<?> list = cavab().jsonPath().getList(listPath);
        Assert.assertNotNull(list, "'" + listPath + "' massivi null-dur");
        Assert.assertEquals(list.size(), count,
                "'" + countPath + "' say massiv ölçüsünə bərabər deyil");
    }


    @Step("<listPath> massivi <csv> dəyərlərindən ibarətdir")
    public void massivDeyerleri(String listPath, String csv) {
        List<String> actual = cavab().jsonPath().getList(listPath);
        List<String> expected = Arrays.asList(csv.split("\\s*,\\s*"));
        Assert.assertEquals(actual, expected,
                "'" + listPath + "' massivinin dəyərləri uyğun gəlmir");
    }
}