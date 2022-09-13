package room;

import javax.swing.ImageIcon;

/** 
 * Representation of a record from the `rooms` table.
 * @implNote ImageIcon must retrieved from the URL
 * @see Manager
 */
public record Info(
    String id, 
    Type type, 
    int size,
    String url, 
    ImageIcon preview
) {};