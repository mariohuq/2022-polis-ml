# Какие плюсы и недостатки у Merge Sort Join в отличии от Hash Join? (1 балл)



# Соберите WordCount приложение, запустите на датасете ppkm_sentiment (1 балл)

1. Скачать образ `img-hdp-zeppelin`.
  
    ```shell
    $ cd 03-spark/img-hdp-zeppelin
    ```
2. Сборка образа:
  
    ```shell
    $ systemctl start docker.service
    $ docker build -t img-hdp-zeppelin .
    ```
3. Первый старт образа:
  
    ```shell
    $ docker run -it --name zeppelin \
      -p 50090:50090 \
      -p 50075:50075 \
      -p 50070:50070 \
      -p 8042:8042 \
      -p 8088:8088 \
      -p 4040:4040 \
      -p 4044:4044 \
      -p 8888:8888 \
      --hostname localhost \
      img-hdp-zeppelin
    ```

    Последующие запуски:
    
    ```shell
    $ docker start -i zeppelin
    ```
  <!-- 
  Удаляем контейнер и образ
  $ docker rm zeppelin
  $ docker rmi img-hdp-zeppelin
  $ docker system prune
   -->
4. 

# Измените WordCount так, чтобы он удалял знаки препинания и приводил все слова к единому регистру (1 балл)



# Измените выход WordCount так, чтобы сортировка была по количеству повторений, а список слов был во втором столбце, а не в первом (1 балл)



# Измените выход WordCount, чтобы формат соответствовал TSV (1 балл)



# Добавьте в WordCount возможность через конфигурацию задать список стоп-слов, которые будут отфильтрованы во время работы приложения (2 балла)



# Выполните broadcast join на двух датасетах, не используя метод `join()`. Можно использовать любые предварительные трансформации. В качестве исходных данных возьмите `Company.csv` и `Company_Tweet.csv` из датасета [Tweets about the Top Companies from 2005 to 2020](https://www.kaggle.com/omermetinn/tweets-about-the-top-companies-from-2015-to-2020) (3 балла)