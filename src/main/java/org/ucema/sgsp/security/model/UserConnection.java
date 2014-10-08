package org.ucema.sgsp.security.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "UserConnections", uniqueConstraints = @UniqueConstraint(name = "UserConnectionsRank", columnNames = {
		"userId", "providerId", "rank" }))
public class UserConnection {

	@EmbeddedId
	private UserConnectionPK userConnectionPK;
	private Integer rank;
	@Column(length = 255)
	private String displayName;
	@Column(length = 512)
	private String profileUrl;
	@Column(length = 512)
	private String imageUrl;
	@Column(length = 255)
	private String accessToken;
	@Column(length = 255)
	private String secret;
	@Column(length = 255)
	private String refreshToken;
	private Long expireTime;

	public UserConnectionPK getUserConnectionPK() {
		return userConnectionPK;
	}

	public void setUserConnectionPK(UserConnectionPK userConnectionPK) {
		this.userConnectionPK = userConnectionPK;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}