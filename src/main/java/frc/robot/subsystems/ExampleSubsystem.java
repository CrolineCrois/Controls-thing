// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.net.CacheRequest;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

import edu.wpi.first.wpilibj.XboxController;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {

    motorlaunch1.restoreFactoryDefaults();
    motorlaunch2.restoreFactoryDefaults();
    motorlaunch1.setIdleMode(IdleMode.kCoast);
    motorlaunch2.setIdleMode(IdleMode.kCoast);
    motorbottom.setNeutralMode(NeutralMode.Brake);

    motorlaunch2.follow(motorlaunch1);
  }

  // motors
  private final WPI_TalonSRX motorbottom = new WPI_TalonSRX(8);
  private final CANSparkMax motorlaunch1 = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax motorlaunch2 = new CANSparkMax(16, MotorType.kBrushless);
  // distance sensors
  private final AnalogPotentiometer potentbottom = new AnalogPotentiometer(0);
  private final AnalogPotentiometer potentdeck = new AnalogPotentiometer(1);
  private final AnalogPotentiometer potentlaunch = new AnalogPotentiometer(2);
  // button
  private final XboxController controller = new XboxController(0);
  // Sensor numbers
  private double distancebottom;
  private double distancedeck;
  private double distancelaunch;
  private double highdistbot = 0.37;
  private double highdistdeck = 0.30;
  private double highdistlaunch = 0.123;
  private int state = 0;

  // Button Boolean
  private boolean buttoncheck;

  @Override
  public void periodic() {
    distancebottom = potentbottom.get();
    distancedeck = potentdeck.get();
    distancelaunch = potentlaunch.get();
    buttoncheck = controller.getAButtonPressed();

    if (distancebottom >= highdistbot) {
      if (distancedeck < highdistdeck) {
        state = 1;
      } else if (distancelaunch < highdistlaunch) {
        state = 2;
      } 
    }

    if (state == 1) {
      if (distancedeck < highdistdeck) {
        motorbottom.set(-.5);
      } else {
        state = 0;
      }
    }

    if (state == 2) {
      if (distancelaunch < highdistlaunch) {
        motorbottom.set(-.5);
      } else {
        state = 0;
      }
    }

    if (state == 0){
      motorbottom.set(0);
    }

    System.out.println(distancedeck);

    if ((buttoncheck) && (distancelaunch >= highdistlaunch)) {
      motorlaunch1.set(-.5);
    }
    else {
      motorlaunch1.set(0);
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
