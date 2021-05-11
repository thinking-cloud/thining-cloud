thinking-cloud
|-------------- core -------| 
|                |          |
|    beans ---> service   proxy   
|      |          
|     api       
|      |        
|---> web

utils、rest


bus impl
                       service 
                          | 
api ---> bus api ---> bus server <--- proxy
                          ^
                          | 
                       bus beans 



