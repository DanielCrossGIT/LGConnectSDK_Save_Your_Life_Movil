package guerrero.arango.miguel.block_a_mouse;

/**
 * Created by vicva on 4/12/2016.
 * Esta clase se encarga de definir todas las acciones y resultados posibles
 * que la aplicacion web entiende.
 *
 * @author Daniela Cruz
 * @author Victor Vasquez
 * @author Andres Revolledo
 */
public class StringsHelper {

    //Parameters
    public static String ACTION = "accion";
    public static String RESULT = "resultado";
    public static String PLAYER = "jugador";
    public static String AVATAR = "avatar";
    public static String DIFFICULTY = "dificultad";
    public static String WORD = "palabra";
    public static String TIPO = "tipo";
    public static String DIRECCION = "direccion";

    //Acciones enviadas
    public static final String CONNECT_TV = "conectarTV";
    public static final String CONNECT_PLAYER = "conectarJugador";
    public static final String REQUEST_START = "empezarJuego";

    public static final String FINISH_GAME = "salir";
    public static final String PLAY_AGAIN = "volverAjugar";
    public static final String SHOW_ABOUT = "mostrarCreditos";
    public static final String ENVIAR_EVENTO = "enviarEvento";


    public static final String DISCONNECT_PLAYER = "desconectarJugador";

    //Acciones recibidas
    public static final String LOAD_INPUT = "cargarInicio";
    public static final String ENABLE_START = "puedeIniciar";
    public static final String UNABLE_START = "bloquearInicio";
    public static final String IS_DRAWER = "esDibujante";

    public static final String START_TURN = "esTurno";
    public static final String END_TURN = "terminarTurno";
    public static final String GAME_WINNER = "ganadorJuego";
    public static final String CLOSE_APP = "cerrarAplicacion";
    public static final String START_OVER = "cargarNuevoInicio";
    public static final String SUCCESS_CONNECTION = "conexionExitosa";
    public static final String BLOQUEO_EXITOSO = "bloqueoExitoso";
    public static final String JUEGO_CANCELADO = "juegoCancelado";
    public static final String JUEGO_FINALIZADO = "juegoFinalizado";
    public static final String LIMIT_PLAYERS = "limiteJugadores";

    public static final String PERDER_VIDA = "perdio";

    public static final String REINICIAR_JUEGO = "cargarNuevoInicio";
    public static final String LOADING_GAME = "cargandoJuego";
    public static final String INVALID_MOVEMENT = "movimientoInvalido";
}