/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ronyrojas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaqueteriaApiException extends Exception {

    private int codigoError;
    private String mensaje;

}
