package com.solommr.model;

import java.util.List;

public class SteamCSGOProfile {

	private PlayerStats playerstats;

	public PlayerStats getPlayerstats() {
		return playerstats;
	}

	public void setPlayerstats(PlayerStats playerstats) {
		this.playerstats = playerstats;
	}

	public static class PlayerStats {
		private String steamID;
		private String gameName;
		private List<Stats> stats;
		private List<Achievements> achievements;

		public String getSteamID() {
			return steamID;
		}

		public void setSteamID(String steamID) {
			this.steamID = steamID;
		}

		public String getGameName() {
			return gameName;
		}

		public void setGameName(String gameName) {
			this.gameName = gameName;
		}

		public List<Stats> getStats() {
			return stats;
		}

		public void setStats(List<Stats> stats) {
			this.stats = stats;
		}

		public List<Achievements> getAchievements() {
			return achievements;
		}

		public void setAchievements(List<Achievements> achievements) {
			this.achievements = achievements;
		}

	}

	public static class Stats {
		private String name;
		private Long value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getValue() {
			return value;
		}

		public void setValue(Long value) {
			this.value = value;
		}

	}

	public static class Achievements {
		private String name;
		private Long achieved;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getAchieved() {
			return achieved;
		}

		public void setAchieved(Long achieved) {
			this.achieved = achieved;
		}

	}
}
