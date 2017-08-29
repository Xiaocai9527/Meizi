package com.exsun.meizi.entity.douyu;

/**
 * Created by xiaokun on 2017/8/29.
 */

public class RoomEntity
{
    private int Online;
    private String gameName;
    private String ownerAvatar;
    private String ownerNickName;
    private String roomID;
    private String roomName;
    private String roomSrc;
    private String showDetails;
    private String showStatus;

    public String getRoomID()
    {
        return this.roomID;
    }

    public void setRoomID(String roomID)
    {
        this.roomID = roomID;
    }

    public String getRoomSrc()
    {
        return this.roomSrc;
    }

    public void setRoomSrc(String roomSrc)
    {
        this.roomSrc = roomSrc;
    }

    public String getRoomName()
    {
        return this.roomName;
    }

    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }

    public String getOwnerNickName()
    {
        return this.ownerNickName;
    }

    public void setOwnerNickName(String ownerNickName)
    {
        this.ownerNickName = ownerNickName;
    }

    public String getOwnerAvatar()
    {
        return this.ownerAvatar;
    }

    public void setOwnerAvatar(String ownerAvatar)
    {
        this.ownerAvatar = ownerAvatar;
    }

    public String getGameName()
    {
        return this.gameName;
    }

    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }

    public int getOnline()
    {
        return this.Online;
    }

    public void setOnline(int online)
    {
        this.Online = online;
    }

    public String getShowStatus()
    {
        return this.showStatus;
    }

    public void setShowStatus(String showStatus)
    {
        this.showStatus = showStatus;
    }

    public String getShowDetails()
    {
        return this.showDetails;
    }

    public void setShowDetails(String showDetails)
    {
        this.showDetails = showDetails;
    }
}
