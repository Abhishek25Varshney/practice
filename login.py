from selenium import webdriver 
from time import sleep
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By

driver = webdriver.Chrome(executable_path='C:/Users/Asus/AppData/Local/Programs/Python/Python37-32/Scripts/chromedriver.exe')
driver.get('https://e-gmattest.com/') 
print ("Opened site") 


login = driver.find_element_by_id('loginHeaderBtn')
login.click()

myElem =WebDriverWait(driver, 30).until(EC.invisibility_of_element_located((By.ID, 'loginEmailval')))
sleep(3)
username_box = driver.find_element_by_id('loginEmailval') 
myElem.send_keys("abhishek@e-gmat.com") 

  
password_box = driver.find_element_by_id('loginPass') 
password_box.send_keys("egmat2010") 
  
login_box = driver.find_element_by_id('submitmyform') 
login_box.click() 
sleep(4)
driver.get('https://mockqa.e-gmattest.com/mock-ui/dashboard')
