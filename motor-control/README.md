# motor-control

We're using a teensy 4.1 to control the motors using uLisp.

For now we've just got the uLisp ARM Release in `src/main.ino` with a `src/ulisp_forward.h` header file to declare all the types and functions that the Arduino IDE would normally generate, this lets the uLisp code refer to functions and types that are defined later in the file.

## Running

We've got the `platformio-mode` package in emacs, so we can build the project with `C-c i b`, we can upload it to the teensy with `C-c i u`.

Once the teensy is running uLisp we can interact with the REPL using the platformio device monitor (instead of the Arduino IDE serial monitor) with `C-c i m`.

@NOTE: this doesn't seem to work properly, we see output but can't input. However we can connect from the terminal with `pio device monitor` where we can input our s-expressions to evaluate.

## To Do

- figure out how to read from the sd card in uLisp
- figure out how to control a stepper motor in uLisp
- figure out how to connect to the ESP32 via a serial connection
- define a LispLibrary so we can do things easily
