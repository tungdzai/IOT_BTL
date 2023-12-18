package org.ptit.iotserver.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iotserver.constants.ManualMode;
import org.ptit.iotserver.constants.OperationMode;
import org.ptit.iotserver.entity.ControlMode;

@Data
@NoArgsConstructor
public class ControlModeResponse {
  private OperationMode operationMode;
  private ManualMode manualMode;

  public static ControlModeResponse from(ControlMode controlMode) {
    ControlModeResponse response = new ControlModeResponse();
    response.setOperationMode(controlMode.getOperationMode());
    response.setManualMode(controlMode.getManualMode());
    return response;
  }
}
