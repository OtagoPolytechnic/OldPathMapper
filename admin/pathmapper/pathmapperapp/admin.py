from django.contrib import admin

from .models import Plant, Collection, PlantCollection, Track

admin.site.register(Plant)
admin.site.register(Collection)
admin.site.register(PlantCollection)
admin.site.register(Track)