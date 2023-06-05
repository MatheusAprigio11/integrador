from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
import pandas as pd
marcas_digitadas = []

class Web:
    def __init__(self):
        # self.marca = marca
        self.site = f'https://matheusaprigio11.github.io/jeshoes/shoes.html'
        # marcas_digitadas.append(marca)
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
        self.abrir_site()
    def abrir_site(self):
        self.driver.get(self.site)
        sleep(5)
        results = []
        for i in range(1, 11):
            legend = self.driver.find_element(By.XPATH, self.map['nome']['xpath'].replace('%desc%', f'{i}')).text

            valor = self.driver.find_element(By.XPATH, self.map['nums']['xpath'].replace('@valor@', f'{i}')).text
            results.append((legend, valor))

            print(f"{legend}, {valor}")
        dataframe = pd.DataFrame(results)
        dataframe.to_excel('./shoes.xlsx')

f = Web()