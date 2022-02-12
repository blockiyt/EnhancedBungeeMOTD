/**
 *
 * Copyright (c) 2020-2022 Red Coke Development
 * https://github.com/RedCokeDevelopment/MCServerPing/blob/master/LICENSE
 *
 * Forked by me: https://github.com/blockiyt/MCServerPing
 *
 */
package de.blocki.enhancedplugins.enhancedbungeemotd.utils.pinger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class MCServerPingResponse {
  private final int ping;
  private final String version;
  private final int protocol;
  private final int maxPlayers;
  private final int onlinePlayers;
  private final String motd;
  private final JsonArray descriptionExtras;

  public MCServerPingResponse(int ping, String name, int protocol, int playerMax, int playerOnline, String motd, JsonArray descriptionExtras) {
    this.ping = ping;
    this.version = name;
    this.protocol = protocol;
    this.maxPlayers = playerMax;
    this.onlinePlayers = playerOnline;
    this.motd = motd;
    this.descriptionExtras = descriptionExtras;
  }

  public static MCServerPingResponse serverPingFromJsonObj(JsonObject jsonObj) {
    return new MCServerPingResponse(
            jsonObj.get("ping").getAsInt(),
            jsonObj.get("version").getAsJsonObject().get("name").getAsString(),
            jsonObj.get("version").getAsJsonObject().get("protocol").getAsInt(),
            jsonObj.get("players").getAsJsonObject().get("max").getAsInt(),
            jsonObj.get("players").getAsJsonObject().get("online").getAsInt(),
            jsonObj.get("description").getAsJsonObject().get("text").getAsString(),
            (jsonObj.get("description").getAsJsonObject().get("extra") == null) ? null : jsonObj.get("description").getAsJsonObject().get("extra").getAsJsonArray()
    );
  }

  public int getPing() {
    return ping;
  }

  public String getName() {
    return version;
  }

  public int getProtocol() {
    return protocol;
  }

  public int getPlayerMax() {
    return maxPlayers;
  }

  public int getPlayerOnline() {
    return onlinePlayers;
  }

  public String getMotd() {
    return motd;
  }

  public JsonArray getDescriptionExtras() {
    return descriptionExtras;
  }

  public String getAsJsonString() {
    return new Gson().newBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(this);
  }

  public JsonObject getAsJsonObject() {
    return JsonParser.parseString(getAsJsonString()).getAsJsonObject();
  }
}