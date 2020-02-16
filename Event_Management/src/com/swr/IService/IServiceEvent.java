/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swr.IService;

/**
 *
 * @author Eya
 */
import java.sql.SQLException;
import java.util.List;

public interface IServiceEvent<T> {
     void addEvent(T t) throws SQLException;
    boolean deleteEvent(T t) throws SQLException;
    boolean updateEvent(T t) throws SQLException;
    List<T> readAllEvents() throws SQLException;
    boolean updateStateEvent(T t) throws SQLException;
    boolean updateSNbparticipantEvent(T t) throws SQLException;
    boolean decrementNbparticipantEvent(T t) throws SQLException;
    boolean updateNbViewsEvent(T t) throws SQLException;
    boolean updateNbSponsorEvent(T t) throws SQLException;
    boolean updateNbreportEvent(T t) throws SQLException;
     boolean updateStillneededEvent(T t) throws SQLException;
    List<T> TrierEventNbparticipant() throws SQLException;
    List<T> TrierEventNbviews() throws SQLException;
    List<T> TrierEventNbsponsor() throws SQLException;
    List<T> TrierEventDate() throws SQLException;
    List<T> ChercherEvent(String s) throws SQLException;
    T readBudget_StillneededEvents(T t) throws SQLException;
}
