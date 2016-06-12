"""pathmapper URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from pathmapperapp import views

urlpatterns = [
    url(r'^admin/', admin.site.urls),
    url(r'^jsonplant/', views.plant_json),
    url(r'^jsoncollection/', views.collection_json),
    url(r'^jsonplantcollection/', views.plantCollection_json),
    url(r'^jsontrackpoint/', views.trackPoint_json),
    url(r'^jsontrack/', views.track_json),
    url(r'^jsonupdate/', views.updateManager_json),
]