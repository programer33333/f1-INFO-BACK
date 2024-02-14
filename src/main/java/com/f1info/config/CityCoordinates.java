package com.f1info.config;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityCoordinates {

    private String latitude;
    private String longitude;

    public void setCoordinates(String town) {
        switch (town) {
            case "Melbourne" -> {
                latitude = "-37.8140000";
                longitude = "144.9633200";
            }
            case "Sakhir" -> {
                latitude = "26.0344829";
                longitude = "50.5146095";
            }
            case "Hanoï" -> {
                latitude = "21.0245000";
                longitude = "105.8411700";
            }
            case "Anting" -> {
                latitude = "31.3249908";
                longitude = "121.2074925";
            }
            case "Zandvoort" -> {
                latitude = "52.3712500";
                longitude = "4.5330600";
            }
            case " Montmeló" -> {
                latitude = "41.5709992";
                longitude = "2.2603487";
            }
            case "Monte-Carlo" -> {
                latitude = "43.738596850993254";
                longitude = "7.423871239334389";
            }
            case "Bakou" -> {
                latitude = "40.37644855269955";
                longitude = "49.84906342062782";
            }
            case "Montréal" -> {
                latitude = "45.52322855442362";
                longitude = "-73.58192998411806";
            }
            case "Le Castellet" -> {
                latitude = "43.2527393263399";
                longitude = "5.7912685503835695";
            }
            case "Spielberg" -> {
                latitude = "47.21975581402658";
                longitude = "14.76470540134543";
            }
            case "Silverstone Northamptonshire" -> {
                latitude = "52.07409398702135";
                longitude = "-1.0218175812833838";
            }
            case "Hockenheim" -> {
                latitude = "49.32898589065502";
                longitude = "8.567992931919926";
            }
            case "Mogyoród" -> {
                latitude = "47.580446746105046";
                longitude = "19.25106445190842";
            }
            case "Francorchamps" -> {
                latitude = "50.43505076832167";
                longitude = "5.9703644766718975";
            }
            case "Monza" -> {
                latitude = "45.61767024023321";
                longitude = "9.280283925104833";
            }
            case "Singapour" -> {
                latitude = "1.2909514613722577";
                longitude = "103.86503336608828";
            }
            case "Sochi" -> {
                latitude = "43.41025786105026";
                longitude = "39.967513631101696";
            }
            case "Suzuka" -> {
                latitude = "34.84548781160879";
                longitude = "136.53832992412413";
            }
            case "Miami" -> {
                latitude = "25.7616798";
                longitude = "-80.1917902";
            }
            case "Las Vegas" -> {
                latitude = "36.1699412";
                longitude = "-115.1398296";
            }
            case "Mexico" -> {
                latitude = "19.432608";
                longitude = "-99.133209";
            }
            case "São Paulo" -> {
                latitude = "-23.550520";
                longitude = "-46.633308";
            }
            case "Yas Marina, Abou Dabi " -> {
                latitude = "24.467159";
                longitude = "54.603676";
            }
            case "Algarve" -> {
                latitude = "37.189371";
                longitude = "-8.446731";
            }
            case "Mugello" -> {
                latitude = "43.999722";
                longitude = "11.371944";
            }
            case "Imola" -> {
                latitude = "44.343889";
                longitude = "11.716667";
            }
            case "Istanbul" -> {
                latitude = "41.008240";
                longitude = "28.978359";
            }
            case "Djeddah" -> {
                latitude = "21.543333";
                longitude = "39.172778";
            }
            case "Losail" -> {
                latitude = "25.472134";
                longitude = "51.490001";
            }
        }
    }
}