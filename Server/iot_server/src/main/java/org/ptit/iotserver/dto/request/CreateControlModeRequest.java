package org.ptit.iotserver.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ptit.iotserver.constants.ManualMode;
import org.ptit.iotserver.constants.OperationMode;
import org.ptit.iotserver.entity.ControlMode;

@Data
@NoArgsConstructor
public class CreateControlModeRequest {
  private OperationMode operationMode;
  private ManualMode manualMode;

  public ControlMode toControlMode() {
    ControlMode controlMode = new ControlMode();
    controlMode.setOperationMode(this.getOperationMode());
    controlMode.setManualMode(this.getManualMode());
    return controlMode;
  }
}
