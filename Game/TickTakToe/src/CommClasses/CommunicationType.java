/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommClasses;

/**
 *
 * @author ToyMaker
 */
public enum CommunicationType {
    login,
    login_accept,
    login_refuse,
    create_game,
    game_created,
    already_joined_game,
    enter_specific_game,
    enter_random_game,
    busy,
    player_not_exists,
    game_not_exist,
    game_accepted,
    
    
    logoff,
    logoff_refuse,
    logoff_accept,
    
    
    added_x,
    added_o,
    restart,
    
    // Polling
    get_status,
    send_status
}