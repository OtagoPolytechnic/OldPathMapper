# -*- coding: utf-8 -*-
# Generated by Django 1.9.8 on 2016-08-01 01:51
from __future__ import unicode_literals

import django.contrib.gis.db.models.fields
from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('gardensapp', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='track',
            name='waypoints',
            field=django.contrib.gis.db.models.fields.LineStringField(default='LINESTRING(0.0, 0.0)', srid=3857),
        ),
    ]
