from selenium import webdriver 
from time import sleep
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By
from pynput.mouse import Button, Controller
import pyautogui  
  
driver = webdriver.Chrome(executable_path='C:/Users/Asus/AppData/Local/Programs/Python/Python37-32/Scripts/chromedriver.exe')
driver.get('https://zoom.us/j/988-654-376') 
print ("Opened site")

sleep(1)
pyautogui.press("tab")
pyautogui.press("tab")
pyautogui.press("enter")

sleep(1)

driver.quit()
