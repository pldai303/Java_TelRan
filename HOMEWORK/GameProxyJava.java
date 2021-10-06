package game.net;

import static game.api.RequestTypesApi.*;

import java.util.*;
import game.dto.*;
import game.services.UserMethods;
import telran.net.JavaClient;
import telran.net.dto.Protocols;
//user send
public class GameProxyJava extends JavaClient implements UserMethods {

	public GameProxyJava(String host, int port, Protocols netProtocol) throws Exception {
		super(host, port, netProtocol);
	}
	private static final long serialVersionUID = 1L;
	@Override
	public UserCodes registerUser(User user) throws Exception {
		return send(REGISTER_USER, user);
	}
	@Override
	public void saveToFile(String fileName) throws Exception {
		 send(SAVE_USERS, fileName);
	}
	@Override
	public void loadFromFile(String fileName) throws Exception {
		send(LOAD_USERS, fileName);
	}
	@Override
	public UserCodes startNewGame(User user) throws Exception {
		return send(START_NEW_GAME, user);
	}
	@Override
	public ArrayList<String> makeMove(String number) throws Exception {
		return send(MAKE_MOVE, number);
	}
	@Override
	public Long getGameId(User user) throws Exception {
		return send(GET_GAMEID, user);
	}
	@Override
	public ArrayList<UserGame> getFinishedGame(User user) throws Exception {
		return send(GET_FINISHED_GAMES, user);
	}


}
