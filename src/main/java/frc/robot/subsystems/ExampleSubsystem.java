// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {
    motor.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kCoast);
    motor2.setIdleMode(IdleMode.kCoast);
    motor2.follow(motor);
  }

  private final CANSparkMax motor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax motor2 = new CANSparkMax(17, MotorType.kBrushless);

  private final AnalogPotentiometer potent1 = new AnalogPotentiometer(0);

  //private final Timer timer = new Timer();

  //private final XboxController controller = new XboxController(0);

  //private boolean motoron = false;

  //private boolean buttoncheck;

  //private double time;

  private double distance; 

  @Override
  public void periodic() {
    distance = potent1.get();
    //System.out.print ("Caroline is superior");
    System.out.print (distance);

    motor.set(1 - distance);

    //buttoncheck = controller.getAButtonPressed();

    // motor.set(controller.getRightTriggerAxis() +
    /*
     * controller.getLeftTriggerAxis());
     * if (buttoncheck) {
     * if (!motoron) {
     * motor.set(1);
     * } else {
     * motor.set(0);
     * }
     * motoron = !motoron;
     * }
     */
    // if (press) motor.set(controller.getRightTriggerAxis() -
    // controller.getLeftTriggerAxis());
    // System.out.println(controller.getRightTriggerAxis());

    /*time = timer.get();
    System.out.print(time);

    if (buttoncheck) {
      timer.start();
      timer.reset();
    }

    if (time <= 4) {
      motor.set(time / 4);
    } else if (time <= 6) {
      motor.set(1);
    } else if (time <= 10) {
      motor.set(1 - (time / 4));
    }

    else {
      motor.set(0);
    }*/

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
