package jats.utfpl.utils;

import java.util.Map;

public class SystemEnv {

	public static String getPATPath() {
		Map<String, String> env = System.getenv();
		String path = env.get("PAT3");
		if (null == path) {
			throw new Error("Environment variable PAT3 is not set.");
		}
		return path;
	}
}
