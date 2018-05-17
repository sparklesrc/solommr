package com.solommr.model;

public class TeamSearchReq {

	private String gameId;
	private String criteria;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public static class TeamSearchResponse {
		private String id;
		private String name;
		private String leader;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLeader() {
			return leader;
		}

		public void setLeader(String leader) {
			this.leader = leader;
		}

	}

	public static class BuildTeamReq {
		private String name;
		private String shortName;
		private String description;
		private String country;

		private Long gameId;
		private Long userId;

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	}

	public static class RecruitPlayerRequest {
		private Long gameId;
		private Long userId;
		private Long clanId;
		private String description;

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}
	}

	public static class DeleteTeamRequest {
		private Long gameId;
		private Long teamId;
		private Boolean isLeader;
		private Long userId;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public Long getTeamId() {
			return teamId;
		}

		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}

		public Boolean getIsLeader() {
			return isLeader;
		}

		public void setIsLeader(Boolean isLeader) {
			this.isLeader = isLeader;
		}

	}
}
