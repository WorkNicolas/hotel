## Main layout:
1. Navbar
2. main content

## User states:
1. guest
2. member

## states:
1. authentication
1. browse
2. booked
3. checkedin

## Implementation
_Note_:
1. Only one JFrame.
2. Use JPanels to switch `views` / do `tabs`.
3. Navbar items are `tabs`.
### state:authentication
* Navbar:
    1. Empty
* Main:
    1. input:email
    2. input:passphrase
    3. button:login
    4. button:guest
    5. button:register

### state:browse
* Navbar:
    1. Reserve
    1. Logout
* Main: 
    Reserve tab:
    JPanel state:
    1. Start with figure 2.
    1. Combined input & payment form
    1. change state to `booked`
    1. goto reservation view

### state:booked
* Navbar:
    1. Reservation Info
    1. Cancel reservation
    1. Logout
* Main:
    info:tab
    1. Do figure 4

### state:checkedin ###
* Navbar
    1. Room service
    1. extend
    1. Leave early
    1. Logout
* Main
    1. tab:service
        1. Do figure 6
    1. tab:extend
        1. New input form
    1. tab:leave
        1. Require authentication

## Constraints
1. Don't use absolute positioning
1. JPanel will 
    1. ...be the root component for each view.
    1. ...consume the entire window's space.
2. After using any GUI builder, adjust the variable names for readability.