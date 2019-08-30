from selenium import webdriver 
from time import sleep
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.by import By

   
def quiz(num_of_questions):
    for i in range(num_of_questions):
        driver.switch_to_frame(driver.find_element_by_tag_name('iframe'))
        first_option=driver.find_element_by_id('a0')
        driver.execute_script("arguments[0].click();", first_option)
        sleep(2)
        next_button = driver.find_element_by_id('button_enable')
        driver.execute_script("arguments[0].click();", next_button)
        sleep(2)
        driver.switch_to_default_content()

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

sleep(5)
take_the_test = driver.find_elements_by_xpath("//*[contains(text(), 'Take The Test')]")[1]

sleep(2)

driver.execute_script("arguments[0].click();", take_the_test)

sleep(1)

quant_input = driver.find_element_by_xpath('//input[@formcontrolname="qr1"]')

verbal_input = driver.find_element_by_xpath('//input[@formcontrolname="vr1"]')

quant_input.send_keys('51')

verbal_input.send_keys('47')

sleep(2)

start_exam_button = driver.find_element_by_class_name('btn_startExam')

start_exam_button.click()

sleep(2)

section_instruction_next_button = driver.find_element_by_class_name('btn_next')

section_instruction_next_button.click()

sleep(5)

quiz(31)

option_break_next = driver.find_element_by_class_name('btn_next')

option_break_next.click()

sleep(2)

option_break_next = driver.find_element_by_class_name('btn_next')

option_break_next.click()

sleep(3)

quiz(36)
