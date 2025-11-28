# time

We're using an ESP32C3 to fetch the current time from the internet.

For now we've just got the uLisp ESP Release in `src/main.ino` with a `src/ulisp_forward.h` header file to declare all the types and functions that the Arduino IDE would normally generate, this lets the uLisp code refer to functions and types that are defined later in the file.

@NOTE: this `uLisp_forward.h` is not identical to the `motor-control` project one

We also had to modify the defines for the legacy esp32 board definition to remove `dacWrite`, 
since our board (Seeed Xiao esp32-C3) doesn't support this.

## Running

We've got the `platformio-mode` package in emacs, so we can build the project with `C-c i b`, we can upload it to the teensy with `C-c i u`.

Once the teensy is running uLisp we can interact with the REPL using the platformio device monitor (instead of the Arduino IDE serial monitor) with `C-c i m`.

@NOTE: this doesn't seem to work properly, we see output but can't input. However we can connect from the terminal with `pio device monitor` where we can input our s-expressions to evaluate.

## To Do

- figure out how to connect to wifi
- figure out how to get UTC time
- figure out how to talk to the teensy via a serial connection
- figure out how to accept wifi creds from the teensy
- define a LispLibrary to make things easy
