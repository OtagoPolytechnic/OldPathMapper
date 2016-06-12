# -*- coding: utf-8 -*-
# Generated by Django 1.9.6 on 2016-05-18 07:43
from __future__ import unicode_literals

from django.db import migrations
import geoposition.fields


class Migration(migrations.Migration):

    dependencies = [
        ('pathmapperapp', '0004_auto_20160518_1931'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='updatemanager',
            name='locationLastUpdate',
        ),
        migrations.AlterField(
            model_name='trackpoint',
            name='location',
            field=geoposition.fields.GeopositionField(max_length=42),
        ),
        migrations.DeleteModel(
            name='Location',
        ),
    ]