from selenium import webdriver 
from time import sleep
import pyautogui  
  
driver = webdriver.Chrome(executable_path='C:/Users/Asus/AppData/Local/Programs/Python/Python37-32/Scripts/chromedriver.exe')
driver.get('https://zoom.us/j/264-243-2785') 
print ("Opened site")

sleep(1)
pyautogui.press("tab")
pyautogui.press("tab")
pyautogui.press("enter")

sleep(1)

driver.quit()
