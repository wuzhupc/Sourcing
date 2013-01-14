//
//  FavoriteUtil.h
//  sourcing
//
//  Created by wuzhu on 13-1-9.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <Foundation/Foundation.h>
#import "BaseVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface
@protocol FavDataChangeDelegate

@optional
-(void)hasRemoveFavData:(NSInteger)kindex data:(BaseVO *)kvo;
-(void)hasAddFavData:(NSInteger)kindex data:(BaseVO *)kvo;
@end

@interface FavoriteUtil : NSObject
{
    NSMutableArray* _dataList;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties
@property (readonly) NSMutableArray *dataList;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods
+(FavoriteUtil *)favoriteUtil;

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
-(BOOL)addFavData:(BaseVO *)kvo;
-(NSInteger)hasFavData:(BaseVO *)kvo;
-(BOOL)removeFavData:(BaseVO *)kvo;
-(BOOL)removeFavDataWithIndex:(NSInteger)kindex;
-(NSInteger)getFavNumber;

@end
