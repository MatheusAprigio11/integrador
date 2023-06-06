from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
import pandas as pd
import inquirer

class Web:
    def __init__(self, product):
        self.product = product
        self.site = f'http://localhost/aprigio/{product}.html'
        self.map = {
            "nome": {
                'xpath': '/html/body/section[3]/div/div[%desc%]/h2'
            },
            "nums": {
                'xpath': '/html/body/section[3]/div/div[@valor@]/span'
            }
        }
        self.driver = webdriver.Chrome()
        self.driver.maximize_window()
        self.abrir_site(product)

    def abrir_site(self, product):
        self.driver.get(self.site)
        sleep(5)
        results = []
        for i in range(1, 11):
            legend = self.driver.find_element(By.XPATH, self.map['nome']['xpath'].replace('%desc%', f'{i}')).text

            valor = self.driver.find_element(By.XPATH, self.map['nums']['xpath'].replace('@valor@', f'{i}')).text
            results.append((legend, valor))

            print(f"{legend}, {valor}")
        dataframe = pd.DataFrame(results)
        dataframe.to_excel(f'./{product}.xlsx')


products = ["shoes", "caps", "tshirts"]

prodc_selected = inquirer.prompt([
    inquirer.List('product',
                    message="||SELECT THE PRODUCT THAT YOUU WANT TO EXPORT||",
                    choices=[*products],
                    ),
])
print(prodc_selected['product'])
f = Web(prodc_selected['product'])
