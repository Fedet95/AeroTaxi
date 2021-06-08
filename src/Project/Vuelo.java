package Project;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Vuelo {

    private String id = UUID.randomUUID().toString();
    private Date fecha;
    private Ciudades origen;
    private Ciudades destino;
    private int acompañantes;
    private Avion avion;
    private float costoVuelo;

    private final Map<Ciudades, Map<Ciudades, Integer>> distancias = new HashMap<>() {{
        put(Ciudades.BSAS, new HashMap<>() {{
            put(Ciudades.CORDOBA, 695);
            put(Ciudades.SANTIAGO, 1400);
            put(Ciudades.MONTEVIDEO, 950);
        }});
        put(Ciudades.CORDOBA, new HashMap<>() {{
            put(Ciudades.MONTEVIDEO, 1190);
            put(Ciudades.SANTIAGO, 1050);
        }});
        put(Ciudades.SANTIAGO, new HashMap<>() {{
            put(Ciudades.MONTEVIDEO, 2100);
        }});

    }};

    public Vuelo(){}





}



