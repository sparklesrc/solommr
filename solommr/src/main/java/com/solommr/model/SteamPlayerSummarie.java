package com.solommr.model;

import java.util.List;

public class SteamPlayerSummarie {

	private Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public static class Response {

		private List<Player> players;

		public List<Player> getPlayers() {
			return players;
		}

		public void setPlayers(List<Player> players) {
			this.players = players;
		}

	}

	public static class Player {

		private String steamid;
		private Integer communityvisibilitystate;
		private Integer profilestate;
		private String personaname;
		private Integer lastlogoff;
		private String profileurl;
		private String avatar;
		private String avatarmedium;
		private String avatarfull;
		private Integer personastate;
		private String realname;
		private String primaryclanid;
		private Integer timecreated;
		private Integer personastateflags;

		public String getSteamid() {
			return steamid;
		}

		public void setSteamid(String steamid) {
			this.steamid = steamid;
		}

		public Integer getCommunityvisibilitystate() {
			return communityvisibilitystate;
		}

		public void setCommunityvisibilitystate(Integer communityvisibilitystate) {
			this.communityvisibilitystate = communityvisibilitystate;
		}

		public Integer getProfilestate() {
			return profilestate;
		}

		public void setProfilestate(Integer profilestate) {
			this.profilestate = profilestate;
		}

		public String getPersonaname() {
			return personaname;
		}

		public void setPersonaname(String personaname) {
			this.personaname = personaname;
		}

		public Integer getLastlogoff() {
			return lastlogoff;
		}

		public void setLastlogoff(Integer lastlogoff) {
			this.lastlogoff = lastlogoff;
		}

		public String getProfileurl() {
			return profileurl;
		}

		public void setProfileurl(String profileurl) {
			this.profileurl = profileurl;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getAvatarmedium() {
			return avatarmedium;
		}

		public void setAvatarmedium(String avatarmedium) {
			this.avatarmedium = avatarmedium;
		}

		public String getAvatarfull() {
			return avatarfull;
		}

		public void setAvatarfull(String avatarfull) {
			this.avatarfull = avatarfull;
		}

		public Integer getPersonastate() {
			return personastate;
		}

		public void setPersonastate(Integer personastate) {
			this.personastate = personastate;
		}

		public String getRealname() {
			return realname;
		}

		public void setRealname(String realname) {
			this.realname = realname;
		}

		public String getPrimaryclanid() {
			return primaryclanid;
		}

		public void setPrimaryclanid(String primaryclanid) {
			this.primaryclanid = primaryclanid;
		}

		public Integer getTimecreated() {
			return timecreated;
		}

		public void setTimecreated(Integer timecreated) {
			this.timecreated = timecreated;
		}

		public Integer getPersonastateflags() {
			return personastateflags;
		}

		public void setPersonastateflags(Integer personastateflags) {
			this.personastateflags = personastateflags;
		}

	}
}
