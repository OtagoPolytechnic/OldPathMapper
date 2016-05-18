# -*- coding: utf-8 -*-
# Generated by Django 1.9.6 on 2016-05-18 07:31
from __future__ import unicode_literals

from django.db import migrations
import geoposition.fields


class Migration(migrations.Migration):

    dependencies = [
        ('pathmapperapp', '0003_pointofinterest'),
    ]

    operations = [
        migrations.AlterField(
            model_name='plant',
            name='location',
            field=geoposition.fields.GeopositionField(max_length=42),
        ),
    ]