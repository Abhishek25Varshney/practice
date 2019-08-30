from selenium import webdriver 
from time import sleep
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By
from pynput.mouse import Button, Controller
   
  
driver = webdriver.Chrome(executable_path='C:/Users/Asus/AppData/Local/Programs/Python/Python37-32/Scripts/chromedriver.exe')
driver.get('https://zoom.us/j/359-569-0985') 
print ("Opened site")

sleep(2)
print ("Alert")

mouse = Controller()

# Read pointer position
print('The current pointer position is {0}'.format(
    mouse.position))

mouse.position = (568, 194)
print('Now we have moved it to {0}'.format(
    mouse.position))

mouse.press(Button.left)
mouse.release(Button.left)

sleep(1)
driver.quit()
